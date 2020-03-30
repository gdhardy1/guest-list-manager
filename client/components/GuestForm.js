import React, { useState } from "react";
import PropTypes from "prop-types";
import axios from "axios";
import ErrorService from "../service/ErrorService";
import { Form, FormGroup, TextInput, Button } from "carbon-components-react";
import styled from "@emotion/styled";

let StyledFormGroup = styled(FormGroup)`
  margin-bottom: 5px;
  margin-top: 15px;

  .bx--form-item {
    margin-bottom: 10px;
  }
`;

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
      <StyledFormGroup legendText="Guest Details">
        <TextInput
          id="firstName"
          type="text"
          name="firstName"
          labelText="First name"
          placeholder="First Name"
          onChange={handleFormChange}
        />
        <TextInput
          id="lastName"
          type="text"
          name="lastName"
          labelText="Last Name"
          placeholder="Last Name"
          onChange={handleFormChange}
        />
        <TextInput
          id="email"
          type="text"
          name="email"
          labelText="Email"
          placeholder="Email"
          onChange={handleFormChange}
        />
      </StyledFormGroup>
      <Button className="submit" size="small" type="button" onClick={sendForm}>
        Add Guest
      </Button>
    </Form>
  );
}

GuestForm.propTypes = {
  setGuestUpdate: PropTypes.func
};
export default GuestForm;
