import axios from "axios";
import React from "react";
import BusinessCard from "../../../src/components/business/businessCard";
import NewBusiness from "../../../src/components/business/newBusiness";
import SearchBusiness from "../../../src/components/business/searchBusiness";

function myBusiness() {
  const userRol = localStorage.getItem("rolUser");

  const baseURL = `http://localhost:8080/business/`;

  // axios
  //   .get(baseURL, {
  //     params: {
  //       userID: userRol
  //     }
  //   })
  //   .then((response) => {
  //     console.log(response.data);
  //     // toast.success("Usuario creado !", {
  //     //   position: toast.POSITION.TOP_CENTER,
  //     // });
  //     // router.push("/landing");
  //   })
  //   .catch((error) => {
  //     // toast.error("Error de registro, pruebe nuevamente", {
  //     //   position: toast.POSITION.TOP_CENTER,
  //     // });
  //     // console.log(error.message);
  //   });

  return (
    <>
      <div className="lg:grid lg:grid-cols-2">
        <SearchBusiness />
        <NewBusiness />
      </div>
      <BusinessCard />
    </>
  );
}

export default myBusiness;
