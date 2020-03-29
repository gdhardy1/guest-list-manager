import "babel-polyfill";
import React, { useState, useEffect, useCallback } from "react";
import ReactDOM from "react-dom";

import GuestList from "./components/GuestList";
import GuestForm from "./components/Guestform";
import axios from "axios";
import ErrorService from "./service/ErrorService";

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
        <h1 className="title">Guest List Manager</h1>
      </div>
      <div className="form-area">
        <button className="addGuest" onClick={() => setShowForm(prev => !prev)}>
          {showForm ? "Hide Form" : "New Guest"}
        </button>
        {showForm && <GuestForm setGuestUpdate={setGuestUpdate} />}
      </div>
      <div className="guest-list-area">
        <GuestList guests={guests} setGuestUpdate={setGuestUpdate} />
      </div>
    </div>
  );
}

ReactDOM.render(<App />, document.getElementById("react"));
