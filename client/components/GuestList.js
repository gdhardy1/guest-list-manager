import React from "react";
import Guest from "./Guest";
import PropTypes from "prop-types";

function GuestList(props) {
  const { guests, setGuestUpdate } = props;

  return (
    <table className="guest-list">
      <tbody>
        <tr>
          <th>First Name</th>
          <th>Last Name</th>
          <th>Email</th>
          <th>RSVP</th>
          <th></th>
        </tr>
        {guests.map(guest => (
          <Guest key={guest.id} guest={guest} setGuestUpdate={setGuestUpdate} />
        ))}
      </tbody>
    </table>
  );
}

GuestList.propTypes = {
  guests: PropTypes.array
};

export default GuestList;
