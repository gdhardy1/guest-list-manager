import React, { useState, useRef } from "react";
import PropTypes from "prop-types";
import axios from "axios";
import ErrorService from "../service/ErrorService";
import styled from "@emotion/styled";
import { TrashCan20, Edit20, Save20 } from "@carbon/icons-react";
import { TooltipIcon } from "carbon-components-react";

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
      max-width: 30px;
      margin-top: -18px;
      padding-left: 2px;
      color: #393939;
    }

    &__arrow {
      transform: translate(0%, -7px);
    }
  }
`;

let StyledCell = styled(TableCell)`
  .bx--form-item {
    // max-width: 80px;
  }
`;

let IconButton = styled(TooltipIcon)`
  &.bx--tooltip__trigger {
    margin-left: 3px;
    margin-right: 3px;
    padding: 6px;
    background-color: #0f62fe;

    svg {
      fill: #ffffff;
    }

    &.save {
      background-color: #24a148;
    }

    &.delete {
      background-color: #da1e28;
    }
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
            <StyledCell key={cell.id}>
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
            </StyledCell>
          );
        }
      })}
      <TableCell>
        {disabled ? (
          <IconButton tooltipText="Edit RVP" onClick={() => setDisabled(false)}>
            <Edit20 aria-label="Edit RSVP" />
          </IconButton>
        ) : (
          <IconButton
            tooltipText="Confirm RSVP"
            className="save"
            size="small"
            onClick={() => rsvpGuest(guest.email, newReply)}
          >
            <Save20 aria-label="Confirm RSVP" />
          </IconButton>
        )}

        <IconButton
          tooltipText="Delete Guest"
          className="delete"
          onClick={() => removeGuest(guest.email)}
        >
          <TrashCan20 aria-label="Delete" />
        </IconButton>
      </TableCell>
    </TableRow>
  );
}

Guest.propTypes = {
  row: PropTypes.object,
  setGuestUpdate: PropTypes.func
};

export default Guest;
