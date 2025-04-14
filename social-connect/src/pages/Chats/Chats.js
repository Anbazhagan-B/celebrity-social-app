import React, { useEffect, useRef, useState } from "react";
import { Client } from "@stomp/stompjs";
import SockJS from "sockjs-client";
import "./Chats.css";
import { useLocation } from "react-router-dom";
const Chats = () => {
  const location = useLocation();
  const { currentUserId, recipientUserId, recipientName } = location.state;
  const [messages, setMessages] = useState([]);
  const [input, setInput] = useState("");
  const clientRef = useRef(null);

  useEffect(() => {
    const socket = new SockJS("http://localhost:8083/ws-chat");
    const client = new Client({
      webSocketFactory: () => socket,
      debug: (str) => console.log(str),
      reconnectDelay: 5000,
    });

    client.onConnect = () => {
      client.subscribe(`/topic/messages/${currentUserId}`, (message) => {
        const msg = JSON.parse(message.body);
        setMessages((prev) => [...prev, msg]);
      });
    };

    client.activate();
    clientRef.current = client;

    return () => client.deactivate();
  }, [currentUserId]);

  const sendMessage = () => {
    const message = {
      fromUserId: currentUserId,
      toUserId: recipientUserId,
      content: input,
      timestamp: new Date().toISOString(),
      status: "SENT",
    };

    clientRef.current.publish({
      destination: "/app/chat.send",
      body: JSON.stringify(message),
    });

    setMessages((prev) => [...prev, message]);
    setInput("");
  };

  return (
    <div className="chat-container">
      <h2 className="sender-name">{recipientName}</h2>
      <div className="messages-container">
        {messages.map((msg, index) => (
          <div
            key={index}
            className={`message ${
              msg.fromUserId === currentUserId ? "sent" : "received"
            }`}
          >
            {msg.content}
          </div>
        ))}
      </div>
      <div className="chat-input">
        <input
          value={input}
          onChange={(e) => setInput(e.target.value)}
          placeholder="Type message..."
        />
        <button onClick={sendMessage}>Send</button>
      </div>
    </div>
  );
};

export default Chats;
