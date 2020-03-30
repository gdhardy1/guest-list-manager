import React from "react";
import Guest from "./Guest";
import PropTypes from "prop-types";
import { iconDownload, iconEdit, iconSettings } from "carbon-icons";
import { DataTable, Button, Select } from "carbon-components-react";

const {
  TableContainer,
  Table,
  TableHead,
  TableRow,
  TableBody,
  TableCell,
  TableHeader,
  TableExpandHeader,
  TableExpandRow,
  TableExpandedRow,
  TableToolbar,
  TableToolbarSearch,
  TableToolbarContent,
  TableToolbarAction
} = DataTable;

function GuestList(props) {
  const { guests, setGuestUpdate } = props;

  const headers = [
    {
      key: "firstName",
      header: "First Name"
    },
    {
      key: "lastName",
      header: "Last Name"
    },
    {
      key: "email",
      header: "Email"
    },
    {
      key: "reply",
      header: "RSVP"
    }
  ];

  return (
    <DataTable
      rows={guests}
      headers={headers}
      render={({
        rows,
        headers,
        getHeaderProps,
        onInputChange,
        selectedRows,
        getTableProps,
        getRowProps
      }) => (
        <TableContainer>
          <TableToolbar>
            {/* pass in `onInputChange` change here to make filtering work */}
            <TableToolbarSearch size="xl" onChange={onInputChange} />
            {/* <TableToolbarContent>
              <Button size="small" kind="primary">
                Add new
              </Button>
            </TableToolbarContent> */}
          </TableToolbar>
          <Table {...getTableProps()}>
            <TableHead>
              <TableRow>
                {/* add the expand header before all other headers */}
                {headers.map(header => (
                  <TableHeader {...getHeaderProps({ header })}>
                    {header.header}
                  </TableHeader>
                ))}
                <TableHeader>Actions</TableHeader>
              </TableRow>
            </TableHead>
            <TableBody>
              {rows.map(row => (
                <Guest key={row.id} row={row} setGuestUpdate={setGuestUpdate} />
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      )}
    />
  );
}

GuestList.propTypes = {
  guests: PropTypes.array,
  setGuestUpdate: PropTypes.func
};

export default GuestList;
