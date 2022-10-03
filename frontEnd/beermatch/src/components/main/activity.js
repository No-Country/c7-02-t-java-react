import React from "react";
import LikesStats from "../stats/likesStats";
import PlacesVisitedStats from "../stats/placesVisitedStats";
import PostStats from "../stats/postStats";

function Activity() {
  return (
    <>
      <div className="mt-4 w-full grid grid-cols-1 md:grid-cols-2 xl:grid-cols-3 gap-4">
        <LikesStats />
        <PostStats />
        <PlacesVisitedStats />
      </div>
    </>
  );
}

export default Activity;
