/* eslint-disable @next/next/no-img-element */
import React from "react";

function Loading() {
  return (
    <div className="bg-white flex h-screen rounded-md justify-center items-center ">
      <div className="mb-auto mt-10">
        <img
          className="animate-spin w-60"
          alt=""
          src="/beer.png"
        />
      </div>
    </div>
  );
}

export default Loading;
