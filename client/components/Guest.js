import React, { useState, useRef } from "react";
import PropTypes from "prop-types";
import axios from "axios";
import ErrorService from "../service/ErrorService";
import styled from "@emotion/styled";
import { TrashCan16, Edit16, Save16 } from "@carbon/icons-react";

import {
  DataTable,
  Button,
  Select,
  SelectItem,
  OverflowMenu,
  OverflowMenuItem
} from "carbon-components-react";

const { TableRow, TableCell } = DataTable;

let StyledSelect = styled(Select)`
  .bx--select {
    &-input {
      margin-top: -18px;
      color: #393939;
    }
  }
`;

let StyledButton = styled(Button)`
  padding: 8px;
  margin-left: 3px;
  margin-right: 3px;

  svg {
    fill: #ffffff;
  }

  &.save {
    background-color: #24a148;
  }
`;

function Guest(props) {
  const { row, setGuestUpdate } = props;

  const guest = {
    firstName: row.cells[0].value,
    lastName: row.cells[1].value,
    email: row.cells[2].value,
    reply: row.cells[3].value
  };
  const [disabled, setDisabled] = useState(true);
  const [newReply, setNewReply] = useState(guest.reply);
  const input = useRef(null);

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
    <TableRow key={row.id}>
      {row.cells.map(cell => {
        if (cell.info.header !== "reply") {
          return <TableCell key={cell.id}>{cell.value}</TableCell>;
        } else {
          return (
            <TableCell key={cell.id}>
              <StyledSelect
                ref={input}
                id={cell.id}
                labelText=""
                value={newReply}
                onChange={() => setNewReply(input.current.value)}
                disabled={disabled}
              >
                <SelectItem text="Going" value="Going" />
                <SelectItem text="Not Going" value="Not Going" />
                <SelectItem text="No Reply" value="No Reply" />
              </StyledSelect>
            </TableCell>
          );
        }
      })}
      <TableCell>
        {disabled ? (
          <StyledButton size="small" onClick={() => setDisabled(false)}>
            <Edit16 aria-label="Edit RSVP" />
          </StyledButton>
        ) : (
          <StyledButton
            className="save"
            size="small"
            onClick={() => rsvpGuest(guest.email, newReply)}
          >
            <Save16 aria-label="Confirm RSVP" />
          </StyledButton>
        )}
        <StyledButton
          size="small"
          kind="danger"
          onClick={() => removeGuest(guest.email)}
        >
          <TrashCan16 aria-label="Delete" />
        </StyledButton>
      </TableCell>
    </TableRow>
  );
}

Guest.propTypes = {
  row: PropTypes.object,
  setGuestUpdate: PropTypes.func
};

export default Guest;
