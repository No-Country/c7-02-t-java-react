import React from "react";
import Business from "../../src/components/business/business";
import axios from "axios";
import Cookies from "js-cookie";
import Loading from "../../src/protectedRoutes/loading";

function business() {
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
    if (token != "") {
      const baseURL = "http://localhost:8080/business";
      // http://localhost:8080/business?city=Cordoba&country=Argentina&order=ASC&page=1&size=3&state=Cordoba
      // http://localhost:8080/business?city=Cordoba&country=Argentina&order=ASC&page=1&size=3&state=Cordova
      axios
        .get(
          baseURL,
          // {
          //   params: {
          //     city: "Cordoba",
          //     country: "Argentina",
          //     order: "ASC",
          //     page: 1,
          //     size: 3,
          //     state: "Cordoba",
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
    }
  }, [userID, token]);

  console.log(allBusiness);

  if (!allBusiness)
    return (
      <>
        <Loading />
      </>
    );
  if (allBusiness) return <Business allBusiness={allBusiness} />;
}

export default business;
