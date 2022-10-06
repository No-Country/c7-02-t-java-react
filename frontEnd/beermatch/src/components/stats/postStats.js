import React from "react";
import { MdOutlineComment } from "react-icons/md";


function PostStats() {
  return (
    <>
      <div className="bg-white shadow rounded-lg p-4 sm:p-6 xl:p-8 border-2 border-yellow-400">
        <div className="flex items-center">
          <div className="flex-shrink-0">
            <span className="text-5xl leading-none font-bold text-PurpleNavy ">
              17
            </span>
            <h3 className="text-xl font-light text-PurpleNavy">mis comentarios</h3>
          </div>
          <div className="ml-5 w-0 flex items-center justify-end flex-1 text-PurpleNavy">
            <MdOutlineComment className="w-12 h-12" />
          </div>
        </div>
      </div>
    </>
  );
}

export default PostStats;
