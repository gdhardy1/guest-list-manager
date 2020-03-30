import "babel-polyfill";
import React, { useState, useEffect, useCallback } from "react";
import ReactDOM from "react-dom";
import { Button } from "carbon-components-react";
import styled from "@emotion/styled";

import GuestList from "./components/GuestList";
import GuestForm from "./components/Guestform";
import axios from "axios";
import ErrorService from "./service/ErrorService";

let Title = styled.h1`
  margin-bottom: 1 rem;
`;
function App() {
  const [guests, setGuests] = useState([]);
  const [showForm, setShowForm] = useState(false);
  const [guestUpdate, setGuestUpdate] = useState(0);

  useEffect(() => {
    const getGuests = async () =>
      await axios
        .get("/api/guests")
        .then(res => {
          setGuests(res.data);
        })
        .catch(ErrorService.handleError);

    getGuests();
  }, [guestUpdate]);

  return (
    <div>
      <div>
        <Title className="title">Guest List Manager</Title>
      </div>
      <div className="form-area">
        <Button className="addGuest" onClick={() => setShowForm(prev => !prev)}>
          {showForm ? "Hide Form" : "New Guest"}
        </Button>
        {showForm && <GuestForm setGuestUpdate={setGuestUpdate} />}
      </div>
      <div className="guest-list-area">
        <GuestList guests={guests} setGuestUpdate={setGuestUpdate} />
      </div>
    </div>
  );
}

ReactDOM.render(<App />, document.getElementById("react"));
