import React from "react";
import BusinessCard from "./businessCard";
import SearchBusiness from "./searchBusiness";

function Business({allBusiness}) {
  return (
    <>
      <SearchBusiness />
      <BusinessCard allBusiness={allBusiness} />
    </>
  );
}



export default Business;
