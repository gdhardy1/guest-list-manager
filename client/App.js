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

  useEffect(() => {
    const getGuests = async () =>
      await axios
        .get("/api/guests")
        .then(res => {
          setGuests(res.data);
        })
        .catch(ErrorService.handleError);

    getGuests();
  }, []);

  return (
    <div>
      <GuestList guests={guests} />
      <button className="addGuest" onClick={() => setShowForm(prev => !prev)}>
        {showForm ? "Hide Form" : "New Guest"}
      </button>
      {showForm && <GuestForm />}
    </div>
  );
}

ReactDOM.render(<App />, document.getElementById("react"));
