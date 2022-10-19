import axios from "axios";
import React from "react";
import BusinessCard from "../../../src/components/business/businessCard";
import NewBusiness from "../../../src/components/business/newBusiness";
import SearchBusiness from "../../../src/components/business/searchBusiness";

function myBusiness() {
  // const userRol = localStorage.getItem("rolUser");
  axios;

  return (
    <>
      <div className="lg:grid lg:grid-cols-2">
        <SearchBusiness />
        <NewBusiness/>
      </div>
      <BusinessCard />
    </>
  );
}

export default myBusiness;
