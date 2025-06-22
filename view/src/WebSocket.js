import SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';
import React, { useEffect , useState} from 'react';
import { orderData, generateTree } from './Utils';
const WebSocket = () => {
    const arr = [];
    const [html, setHtml] = useState('');
    const [nodePositions, setNodePositions] = useState({});
    const [nodesMap, setNodesMap] = useState(new Map());

    const registerPosition = (id, coords) => {
        setNodePositions(prev => ({ ...prev, [id]: coords }));
    };
    useEffect(() => {
        const socket = new SockJS('http://localhost:8081/ws');
        const client = new Client({
            webSocketFactory: () => socket,
            onConnect: () => {
                console.log('Connected');
                client.subscribe('/topic/updates', message => {
                    const messageData = JSON.parse(message.body);
                    console.log('Received from WebSocket:', messageData);
                    if(messageData.message === 'first packet') {
                        arr.length = 0; // Clear the array
                        console.log('First packet received, prepare processing');
                        console.log('clear previous data');
                        console.log('show loading indicator');
                    }
                    else if(messageData?.isLastPacket == true) {
                        console.log(arr);
                        const {nodesMap, id} = orderData(arr);
                        const jsxTree = generateTree(nodesMap, id, 0, 0, registerPosition);
                        setHtml(jsxTree);
                        setNodesMap(nodesMap);
                        console.log('Last packet received, start rendering');
                        
                    }
                    else {
                        arr.push(messageData);
                    }

                });
            }
        });

        client.activate();
        return () => {
            client.deactivate();
        };
    }, []);

    return (
        <div style={{ position: 'relative', display: 'flex' , width: '100vw', height: '100vh', overflow : 'auto'  }}>
              <div style={{ position: 'relative', minWidth: '100vw', minHeight: '2000px' }}>  
                    <svg style={{ position: 'absolute', top: 0, left: 0, width: '100%', height: '100%', pointerEvents: 'none' }}>
                        {
                            Array.from(nodesMap.values()).flatMap(node =>
                                (node.children || []).map(child => {
                                const from = nodePositions[node.currentId];
                                const to = nodePositions[child.currentId];
                                if (!from || !to) return null;
                                return (
                                    <line
                                    key={`${node.currentId}-${child.currentId}`}
                                    x1={from.x}
                                    y1={from.y}
                                    x2={to.x}
                                    y2={to.y}
                                    stroke="red"
                                    strokeWidth="2"
                                    />
                                );
                            })
                        )}
                    </svg>
                    <div style={{ zIndex: 1 }}>
                        {html}
                    </div>
              </div>
        </div>
    )
}

export default WebSocket;