import "babel-polyfill";
import React from "react";
const ReactDOM = require("react-dom");

function App() {
  return <div>Hello from React+Spring Boot</div>;
}

ReactDOM.render(<App />, document.getElementById("react"));
