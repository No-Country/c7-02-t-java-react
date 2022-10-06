import React from "react";
import SearchBusiness from "../business/searchBusiness";
import Post from "../post/post";
import Activity from "./activity";

function Main() {
  return (
    <>
      <div >
        <SearchBusiness />
      </div>
      <div>
        <Activity />
      </div>
      <div>
        <p className="w-auto justify-center flex pt-2 m-2 text-white font-bold text-xl">Mi ultimo comentario</p>
        <Post />
      </div>
    </>
  );
}

export default Main;
