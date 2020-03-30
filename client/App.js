import "babel-polyfill";
import React, { useState, useEffect, useCallback } from "react";
import ReactDOM from "react-dom";
import { Button } from "carbon-components-react";
import styled from "@emotion/styled";

import GuestList from "./components/GuestList";
import GuestForm from "./components/Guestform";
import axios from "axios";
import ErrorService from "./service/ErrorService";

let AppArea = styled.div`
  margin: auto;
  max-width: 1100px;
  height: 100vh;
`;

let Title = styled.h1`
  margin-bottom: 1rem;
`;

let FormArea = styled.div`
  margin-bottom: 1rem;
  max-width: 400px;
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
    <AppArea>
      <div>
        <Title className="title">Guest List Manager</Title>
      </div>
      <FormArea className="form-area">
        <Button
          className="addGuest"
          size="small"
          onClick={() => setShowForm(prev => !prev)}
        >
          {showForm ? "Hide Form" : "New Guest"}
        </Button>
        {showForm && <GuestForm setGuestUpdate={setGuestUpdate} />}
      </FormArea>
      <div className="guest-list-area">
        <GuestList guests={guests} setGuestUpdate={setGuestUpdate} />
      </div>
    </AppArea>
  );
}

ReactDOM.render(<App />, document.getElementById("react"));
