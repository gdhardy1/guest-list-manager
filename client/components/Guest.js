import React from "react";
import PropTypes from "prop-types";
import axios from "axios";
import ErrorService from "../service/ErrorService";

function Guest(props) {
  const { guest } = props;
  const { firstName, lastName, email, reply, id } = guest;
  const removeGuest = email => {
    console.log({ email });
    axios.delete(`/api/guests/${email}`).catch(ErrorService.handleError);
  };
  return (
    <tr>
      <td>{firstName}</td>
      <td>{lastName}</td>
      <td>{email}</td>
      <td>{reply}</td>
      <td>
        <button className="remove" onClick={() => removeGuest(email)}>
          Remove
        </button>
      </td>
    </tr>
  );
}

Guest.propTypes = {
  guest: PropTypes.object
};

export default Guest;