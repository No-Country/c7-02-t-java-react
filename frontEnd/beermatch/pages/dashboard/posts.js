import Cookies from "js-cookie";
import React from "react";
import NewPost from "../../src/components/post/newPost";
import Post from "../../src/components/post/post";

function posts() {
  const [userID, setUserID] = React.useState('');
  const [token, setToken] = React.useState('');

  React.useEffect(() => {
    const userID = Cookies.get("userID")
    setUserID(userID);
    const token = Cookies.get("token")
    setToken(token);
  }, [])
  



  return (
    <>
      <NewPost />
      <Post userID={userID} token={token} />
    </>
  );
}

export default posts;
