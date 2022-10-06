import React from "react";
import DataProfile from "./dataProfile";
import PhotoProfile from "./photoProfile";

function Profile() {
  return (
    <div className="bg-white p-2 w-fit justify-center m-auto text-PurpleNavy border-2 border-yellow-400 rounded-lg">
      <PhotoProfile />
      <DataProfile />
    </div>
  );
}

export default Profile;
