import React, { useState } from "react";

import axios from "axios";
import ErrorService from "../service/ErrorService";

function GuestForm(props) {
  const { setGuestUpdate } = props;
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");

  const sendForm = async event => {
    const guest = {
      firstName,
      lastName,
      email
    };

    await axios
      .post("/api/guests", guest)
      .then(res => {
        if (res.status === 201) {
          setGuestUpdate(prev => prev + 1);
        }
      })
      .catch(ErrorService.handleError);
  };

  const handleFormChange = event => {
    const setters = {
      firstName: setFirstName,
      lastName: setLastName,
      email: setEmail
    };

    const setState = setters[event.target.name];

    setState(event.target.value);
  };

  return (
    <form className="form">
      <input
        type="text"
        name="firstName"
        placeholder="First Name"
        onChange={handleFormChange}
      />
      <input
        type="text"
        name="lastName"
        placeholder="Last Name"
        onChange={handleFormChange}
      />
      <input
        type="text"
        name="email"
        placeholder="Email"
        onChange={handleFormChange}
      />
      <button className="submit" type="button" onClick={sendForm}>
        Add Guest
      </button>
    </form>
  );
}

export default GuestForm;
