import Link from "next/link";
import React from "react";
import { ImHeart } from "react-icons/im";
import { IoBeer } from "react-icons/io5";


function LikesStats() {
  return (
    <>
      <div className="bg-white shadow rounded-lg p-4 sm:p-6 xl:p-8 border-2 border-yellow-400 cursor-pointer">
        <Link href="/dashboard/business">
          <div className="flex items-center">
            <div className="flex">
              <span className="text-3xl leading-none font-bold text-PurpleNavy ">
                VAMOS POR UNAS BIRRAS?
              </span>
            </div>
            <div className="ml-5 w-0 flex items-center justify-end flex-1 text-PurpleNavy">
              <IoBeer className="w-12 h-12" />
            </div>
          </div>
        </Link>
      </div>
    </>
  );
}

export default LikesStats;
