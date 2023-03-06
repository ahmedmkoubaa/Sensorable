package com.sensorable;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.commons.SensorTransmissionCoder;
import com.commons.SensorableConstants;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.gms.wearable.CapabilityClient;
import com.google.android.gms.wearable.CapabilityInfo;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.Wearable;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WearSensorDataSender {
    private static final String WEAR_DATA_RECEPTION = "wear_data_reception";
    private static final String WEAR_DATA_RECEPTION_MESSAGE_PATH = "/wear_data_reception";
    private final Activity context;
    private final ExecutorService executorService = Executors.newFixedThreadPool(1);
    private final ArrayList<SensorTransmissionCoder.SensorMessage> sensorsBuffer = new ArrayList<>();

    public WearSensorDataSender(Activity context) {
        this.context = context;
    }

    public void sendMessage(int device_type, int sensorType, float[] value) {
        sendMessage(new SensorTransmissionCoder.SensorMessage(device_type, sensorType, value));
    }

    public void sendMessage(final SensorTransmissionCoder.SensorMessage message) {
        sensorsBuffer.add(message);

        if (sensorsBuffer.size() == SensorableConstants.WEAR_BUFFER_SIZE) {
            final ArrayList<SensorTransmissionCoder.SensorMessage> sensorsBackUpBuffer = new ArrayList<>(sensorsBuffer);
            sensorsBuffer.clear();

            String sensorsToSend = "";
            for (SensorTransmissionCoder.SensorMessage s : sensorsBackUpBuffer) {
                sensorsToSend += s.toString() + SensorTransmissionCoder.DATA_SEPARATOR;
            }

            final String finalSensorsToSend = sensorsToSend;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    CapabilityInfo capabilityInfo = null;
                    String bestNode = null;

                    try {
                        capabilityInfo = Tasks.await(Wearable
                                .getCapabilityClient(context)
                                .getCapability(WEAR_DATA_RECEPTION, CapabilityClient.FILTER_REACHABLE)
                        );
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (capabilityInfo != null) {
                        bestNode = pickBestNodeId(capabilityInfo.getNodes());
                    }

                    if (bestNode != null) {
                        Task<Integer> sendTask =
                                Wearable.getMessageClient(context).
                                        sendMessage(
                                                bestNode,
                                                WEAR_DATA_RECEPTION_MESSAGE_PATH,
                                                SensorTransmissionCoder.codeString(finalSensorsToSend)
                                        );


                        // You can add success and/or failure listeners,
                        // Or you can call Tasks.await() and catch ExecutionException
                        sendTask.addOnSuccessListener(
                                new OnSuccessListener() {
                                    @Override
                                    public void onSuccess(Object o) {
                                        SensorableLogger.log("Successfully sent sensors to android");
                                    }
                                });


                        sendTask.addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                SensorableLogger.log("Failed to send sensors");
                                sensorsBuffer.addAll(0, sensorsBackUpBuffer);
                            }
                        });
                    } else {
                        // Unable to retrieve node with transcription capability
                        SensorableLogger.log("NO NEARBY NODE");
                    }
                }
            });
        }


    }

    private String pickBestNodeId(Set<Node> nodes) {
        String bestNodeId = null;
        // Find a nearby node or pick one arbitrarily
        for (Node node : nodes) {
            if (node.isNearby()) {
                return node.getId();
            }
            bestNodeId = node.getId();
        }
        return bestNodeId;
    }

}
