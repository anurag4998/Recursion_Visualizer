import React from "react";
import ReactDOM from "react-dom/client";
import WebSocket from "./WebSocket";

const App = () => 
    <div>
        <WebSocket />
    </div>;

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(<App />);