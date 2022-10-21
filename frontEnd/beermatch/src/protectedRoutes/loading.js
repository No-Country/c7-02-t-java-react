/* eslint-disable @next/next/no-img-element */
import React from "react";

function Loading() {
  return (
    <div className="flex h-screen m-auto rounded-md justify-center items-center ">
      <div className="mb-auto mt-10">
        <img
          className=" w-60"
          alt=""
          src="/logo.png"
        />
      </div>
    </div>
  );
}

export default Loading;
