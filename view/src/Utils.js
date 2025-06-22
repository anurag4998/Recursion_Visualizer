import React, { useState, useEffect } from 'react';
import Node from './Node';

const getRoot = (arr) => {
    const root = arr.find(item => item.parentId === null);
    return root;
}

export const orderData = (arr) => {
    const map = new Map();
    const nodesMap = new Map();
    const rootNode = getRoot(arr);
    map.set(rootNode.level, rootNode);
    const queue = [rootNode];

    while(queue.length > 0) {
        const currentNode = queue.shift();
        nodesMap.set(currentNode.currentId, currentNode);
        const children = arr.filter(item => item.parentId === currentNode.currentId);
        children.forEach(child => {
            const levelNodes = map.get(child.level);
            map.set(child.level, [...levelNodes || [], child]);
            nodesMap.set(child.currentId, child);
            queue.push(child);
        });
        currentNode.children = children;
    }
    return {nodesMap, id : rootNode.currentId};
}

export const generateTree = (nodeMap, rootId, posX, posY, registerPosition) => {
    const root = nodeMap.get(rootId);
    if(!root) return null;
    const children = root.children;
    const horizontalSpacing = 100;

    let nodes = [
        generateHTMLForNode(root, posX, posY, registerPosition)
    ];

    let html = generateHTMLForNode(root, posX, posY);
    for(let i = 0; i < children.length; i++) {
        const child = children[i];
        const childPosX = posX + (i * horizontalSpacing);
        const childPosY = posY + 100; // Assuming a vertical spacing of 100px
        const childTree = generateTree(nodeMap, child.currentId, childPosX, childPosY, registerPosition);
        if (childTree) nodes = nodes.concat(childTree);

    }
    return nodes;
}

const generateHTMLForNode = (node, posX, posY, registerPosition) => {
    return (
        <div key={node.currentId} className="node" style={{ left: `${posX}px`, top: `${posY}px`, position: 'absolute' }}>
            <Node node={node} posX={posX} posY={posY} registerPosition={registerPosition} />
        </div>
    )
    
};