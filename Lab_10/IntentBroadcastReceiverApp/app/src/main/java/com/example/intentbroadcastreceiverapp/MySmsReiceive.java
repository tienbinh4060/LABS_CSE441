package com.example.intentbroadcastreceiverapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

// MySmsReceive must extend BroadcastReceiver to correctly override the onReceive method.
public class MySmsReiceive extends BroadcastReceiver {

    // The onReceive method is called when the BroadcastReceiver receives an Intent broadcast.
    @Override
    public void onReceive(Context context, Intent intent) {
        // Call the helper method to process the received SMS.
        processReceive(context, intent);
    }

    /**
     * Processes the incoming SMS message from the received Intent.
     * Extracts sender address and message body, then displays them in a Toast.
     * @param context The Context in which the receiver is running.
     * @param intent The Intent being received.
     */
    public void processReceive(Context context, Intent intent) {
        // Get the SMS message bundle from the Intent extras.
        Bundle extras = intent.getExtras();
        String message = "";
        String body = "";
        String address = "";

        // Check if the bundle contains any extras.
        if (extras != null) {
            // "pdus" is the key for the PDU (Protocol Data Unit) array containing SMS messages.
            Object[] smsExtra = (Object[]) extras.get("pdus");

            // Iterate through the array of PDUs to extract each SMS message.
            for (int i = 0; i < smsExtra.length; i++) {
                // Create an SmsMessage object from the PDU byte array.
                SmsMessage sms = SmsMessage.createFromPdu((byte[]) smsExtra[i]);

                // Get the message body and originating address.
                body = sms.getMessageBody();
                address = sms.getOriginatingAddress();

                // Append the message details to the display string.
                message += "Có 1 tin nhắn từ " + address + "\n" + body + " vừa gởi đến";
            }
            // Display the compiled message in a long-duration Toast.
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }
}