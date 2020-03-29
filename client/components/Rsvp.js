import React, { useState, useRef } from "react";

export default function Rsvp(props) {
  const { disabled, newReply, setNewReply } = props;
  const input = useRef(null);

  const handleChange = () => {
    setNewReply(input.current.value);
  };

  return (
    <select
      ref={input}
      disabled={disabled}
      value={newReply}
      onChange={handleChange}
    >
      <option value="Going">Going</option>
      <option value="Not Going">Not Going</option>
      <option value="No Reply">No Reply</option>
    </select>
  );
}
