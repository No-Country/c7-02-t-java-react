import React from "react";
import { IoBeer } from "react-icons/io5";
import Link from "next/link";


function PlacesVisitedStats() {
  return (
    <>
      <div className="bg-white shadow rounded-lg p-4 sm:p-6 xl:p-8 border-2 border-yellow-400">
        <Link href="/dashboard/posts" >
        <div className="flex items-center">
          <div className="flex">
            <span className="text-3xl leading-none font-bold text-PurpleNavy ">
              MIS ULTIMOS COMENTARIOS
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

export default PlacesVisitedStats;
