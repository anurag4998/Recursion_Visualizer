import React, { useRef, useEffect } from 'react';

const Node = (props) => {
    const { node, posX, posY, registerPosition} = props;
    const nodeRef = useRef();

    useEffect(() => {
        // const rect = nodeRef.current.getBoundingClientRect();
        // const centerX = posX + rect.width / 2;
        // const centerY = posY + rect.height / 2;
        // registerPosition(node.currentId, { x: centerX, y: centerY });
        const raf = requestAnimationFrame(() => {
            if (!nodeRef.current) return;
            const rect = nodeRef.current.getBoundingClientRect();
            const centerX = posX + rect.width / 2;
            const centerY = posY + rect.height / 2;
            registerPosition(node.currentId, { x: centerX, y: centerY });
        });
        return () => cancelAnimationFrame(raf);
    }, [posX, posY, node.currentId, registerPosition]);

    return (
        <div 
        className="node" 
        ref={nodeRef}
        style={
            { left: `${posX}px`, 
              top: `${posY}px`,
              backgroundColor: 'lightblue',
              border: '2px solid #333',
              borderRadius: '50%',
              margin: '10px',
              width: '80px',
              height: '80px',
              textAlign: 'center',
            }}>
            <h6>{node.message}</h6>
        </div>
    );
}

export default Node;