import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import {
  fetchConnections,
  searchUsernames,
} from "../../redux/Actions/connectionActions";
import { useNavigate } from "react-router-dom";
import { setHeaderTitle } from "../../redux/Actions/headerActions";
import "./Connections.css";

const Connections = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const [searchTerm, setSearchTerm] = useState("");
  const { connections, searchResults } = useSelector(
    (state) => state.connections
  );
  const { userInfo } = useSelector((state) => state.user);

  useEffect(() => {
    if (userInfo?.id > 0) {
      dispatch(fetchConnections(userInfo.id));
      dispatch(setHeaderTitle("Connections"));
    }
  }, [userInfo]);

  useEffect(() => {
    if (searchTerm.trim() !== "") {
      dispatch(searchUsernames(searchTerm));
    }
  }, [searchTerm]);

  const handleConnectionClick = (recipientUserId, recipientName) => {
    navigate("/chat", {
      state: { currentUserId: userInfo.id, recipientUserId, recipientName },
    });
  };

  return (
    <div className="connections-container">
      {/* <input
        type="text"
        className="connection-search"
        placeholder="Search usernames..."
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
      />

      {searchTerm && searchResults?.length > 0 && (
        <ul className="search-suggestions">
          {searchResults.map((name) => (
            <li key={name} className="search-item">
              {name}
            </li>
          ))}
        </ul>
      )} */}

      <ul className="connection-list">
        {connections.map((conn) => (
          <li
            key={conn.userId}
            onClick={() => handleConnectionClick(conn.userId, conn.name)}
            className="connection-item"
          >
            {conn.status === "CONNECTED" && <div className="status-dot" />}
            <span className="connection-name">{conn.name}</span>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Connections;
