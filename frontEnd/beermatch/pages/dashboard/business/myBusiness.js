import axios from "axios";
import Cookies from "js-cookie";
import React from "react";
import BusinessCard from "../../../src/components/business/businessCard";
import NewBusiness from "../../../src/components/business/newBusiness";
import SearchBusiness from "../../../src/components/business/searchBusiness";
import Loading from "../../../src/protectedRoutes/loading";

function myBusiness() {
  const [allBusiness, setAllBusiness] = React.useState("");
  const [userID, setUserID] = React.useState("");
  const [token, setToken] = React.useState("");

  React.useEffect(() => {
    const userID = Cookies.get("userID");
    setUserID(userID);
    const token = Cookies.get("token");
    setToken(token);
  }, []);

  console.log(userID);
  console.log(token);

  React.useEffect(() => {
    const baseURL = `http://localhost:8080/business/getByUser?order=${"ASC"}&page=${1}&size=${5}&userID=${userID}`;
    axios
      .get(
        baseURL,
        // {
        //   params: {
        //     order: "ASC",
        //     userID: 1,
        //   },
        // },
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      .then((response) => {
        console.log(response.data);
        setAllBusiness(response.data.entities);
        // toast.success("Usuario creado !", {
        //   position: toast.POSITION.TOP_CENTER,
        // });
      })
      .catch((error) => {
        // toast.error("Error de registro, pruebe nuevamente", {
        //   position: toast.POSITION.TOP_CENTER,
        // });
        console.log(error.message);
      });
  }, [userID, token]);

  console.log(allBusiness);

  if (!allBusiness) return <Loading />

  if (allBusiness)
    return (
      <>
        <div className="lg:grid lg:grid-cols-2">
          <SearchBusiness />
          <NewBusiness />
        </div>
        <BusinessCard allBusiness={allBusiness} />
      </>
    );
}

export default myBusiness;
