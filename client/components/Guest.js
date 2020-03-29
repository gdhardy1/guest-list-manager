import React, { useState } from "react";
import PropTypes from "prop-types";
import axios from "axios";
import ErrorService from "../service/ErrorService";
import Rsvp from "./Rsvp";

function Guest(props) {
  const { guest, setGuestUpdate } = props;
  const { firstName, lastName, email, reply, id } = guest;

  const [disabled, setDisabled] = useState(true);
  const [newReply, setNewReply] = useState(reply);

  const removeGuest = async email => {
    await axios
      .delete(`/api/guests/${email}`)
      .then(res => {
        if (res.status === 204) {
          setGuestUpdate(prev => prev + 1);
        }
      })
      .catch(ErrorService.handleError);
  };

  const rsvpGuest = async (email, newReply) => {
    let reply = {
      reply: newReply
    };

    await axios
      .patch(`/api/guests/${email}/rsvp`, reply)
      .then(res => {
        if (res.status === 204) {
          setGuestUpdate(prev => prev + 1);
        }
      })
      .catch(ErrorService.handleError);

    setDisabled(true);
  };

  const enable = () => {
    setDisabled(false);
  };

  return (
    <tr>
      <td>{firstName}</td>
      <td>{lastName}</td>
      <td>{email}</td>
      <td>
        <Rsvp
          disabled={disabled}
          setNewReply={setNewReply}
          newReply={newReply}
        />
      </td>
      <td>
        {disabled ? (
          <button className="remove" onClick={() => setDisabled(false)}>
            Change RSVP
          </button>
        ) : (
          <button className="remove" onClick={() => rsvpGuest(email, newReply)}>
            Confirm RSVP
          </button>
        )}
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
