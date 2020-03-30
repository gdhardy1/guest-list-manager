import React, { useState } from "react";
import PropTypes from "prop-types";
import axios from "axios";
import ErrorService from "../service/ErrorService";
import { Form, FormGroup, TextInput, Button } from "carbon-components-react";

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
    <Form className="form">
      <TextInput
        type="text"
        name="firstName"
        placeholder="First Name"
        onChange={handleFormChange}
      />
      <TextInput
        type="text"
        name="lastName"
        placeholder="Last Name"
        onChange={handleFormChange}
      />
      <TextInput
        type="text"
        name="email"
        placeholder="Email"
        onChange={handleFormChange}
      />
      <Button className="submit" type="button" onClick={sendForm}>
        Add Guest
      </Button>
    </Form>
  );
}

GuestForm.propTypes = {
  setGuestUpdate: PropTypes.func
};
export default GuestForm;
