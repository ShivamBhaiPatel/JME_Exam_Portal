import React from "react";
import { MDBContainer, MDBRow, MDBCol } from "mdbreact";
import './App.css'; //Import here your file style

const gridExamplesPage = () => {
return (
<MDBContainer>
  <MDBRow>
    <MDBCol md="3">.col-md-3</MDBCol>
    <MDBCol md="6">.col-md-4</MDBCol>
    <MDBCol md="3">.col-md-4</MDBCol>
  </MDBRow>
</MDBContainer>
);
}

export default gridExamplesPage;