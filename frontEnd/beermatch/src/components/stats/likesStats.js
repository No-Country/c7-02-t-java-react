import React from "react";
import { ImHeart } from "react-icons/im";

function LikesStats() {
  return (
    <>
      <div className="bg-white shadow rounded-lg p-4 sm:p-6 xl:p-8 border-2 border-yellow-400">
        <div className="flex items-center">
          <div className="flex-shrink-0">
            <span className="text-5xl leading-none font-bold text-PurpleNavy ">
              24
            </span>
            <h3 className="text-xl font-light text-PurpleNavy">
              mis Likes
            </h3>
          </div>
          <div className="ml-5 w-0 flex items-center justify-end flex-1 text-PurpleNavy">
            <ImHeart className="w-12 h-12"/>
          </div>
        </div>
      </div>
    </>
  );
}

export default LikesStats;
