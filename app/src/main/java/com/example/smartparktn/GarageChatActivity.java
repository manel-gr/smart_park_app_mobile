package com.example.smartparktn;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class GarageChatActivity extends AppCompatActivity {
    private ImageButton backButton;
    private ImageButton callButton;
    private RecyclerView chatRecyclerView;
    private EditText messageInput;
    private ImageButton sendButton;
    private TextView agentName;
    private ImageView agentImage;
    private List<ChatMessage> messages;
    private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage_chat);

        initializeViews();
        setupClickListeners();
        setupChat();
    }

    private void initializeViews() {
        backButton = findViewById(R.id.backButton);
        callButton = findViewById(R.id.callButton);
        chatRecyclerView = findViewById(R.id.chatRecyclerView);
        messageInput = findViewById(R.id.messageInput);
        sendButton = findViewById(R.id.sendButton);
        agentName = findViewById(R.id.agentName);
        agentImage = findViewById(R.id.agentImage);

        agentName.setText("Garage Customer Service");
    }

    private void setupClickListeners() {
        backButton.setOnClickListener(v -> finish());

        callButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, GarageCallActivity.class);
            startActivity(intent);
        });

        sendButton.setOnClickListener(v -> sendMessage());
    }

    private void setupChat() {
        messages = new ArrayList<>();
        messages.add(new ChatMessage("Welcome to Garage Customer Service", true));

        chatAdapter = new ChatAdapter(messages);
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatRecyclerView.setAdapter(chatAdapter);
    }

    private void sendMessage() {
        String messageText = messageInput.getText().toString().trim();
        if (!messageText.isEmpty()) {
            messages.add(new ChatMessage(messageText, false));
            chatAdapter.notifyItemInserted(messages.size() - 1);
            messageInput.setText("");
            chatRecyclerView.scrollToPosition(messages.size() - 1);

            // Simulate response after 1 second
            chatRecyclerView.postDelayed(() -> {
                messages.add(new ChatMessage("Thank you for your message. Our team will assist you shortly.", true));
                chatAdapter.notifyItemInserted(messages.size() - 1);
                chatRecyclerView.scrollToPosition(messages.size() - 1);
            }, 1000);
        }
    }
}

