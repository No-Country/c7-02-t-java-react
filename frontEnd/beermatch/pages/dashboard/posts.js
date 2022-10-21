import axios from "axios";
import Cookies from "js-cookie";
import React from "react";
import NewPost from "../../src/components/post/newPost";
import Post from "../../src/components/post/post";

function posts() {
  const [userID, setUserID] = React.useState('');
  const [token, setToken] = React.useState('');
  const [allBusiness, setAllBusiness] = React.useState("");


  React.useEffect(() => {
    const userID = Cookies.get("userID")
    setUserID(userID);
    const token = Cookies.get("token")
    setToken(token);
  }, [])

  
  React.useEffect(() => {
    if (token != "") {
      const baseURL = "http://localhost:8080/business";

      axios
        .get(baseURL, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        })
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



if (!userID && !token && !allBusiness)return (
  <>
  <h1>no hay</h1>
  </>
)
if (userID && token && allBusiness) 
  return (
    <>
      <NewPost allBusiness={allBusiness} />
      <Post allBusiness={allBusiness} userID={userID} token={token} />
    </>
  );
}

export default posts;
