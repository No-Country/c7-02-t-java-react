import axios from "axios";
import React from "react";
import PostCard from "./postCard";

function Post({ allBusiness, userID, token }) {
  const [allPosts, setAllPosts] = React.useState("");

  const baseURL = `http://localhost:8080/review/user?&userID=${userID}`;

  console.log(userID);

  React.useEffect(() => {
    axios
      .get(
        baseURL,
        // {
        //   params: {
        //     order: "ASC",
        //     userID: 1,
        //   }
        // },
        {
          headers: {
            Authorization: `Bearer ${token}`,
            // 'Access-Control-Allow-Origin': '*',
          },
        }
      )
      .then((response) => {
        console.log(response.data);
        setAllPosts(response.data);
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
  }, []);

  console.log(allPosts);

  if (!allPosts) return <>NO HAY</>;
  if (allPosts)
    return <PostCard allPosts={allPosts} allBusiness={allBusiness} />;
}

export default Post;
