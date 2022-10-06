import React from "react";

function Post() {
  return (
    <div className=" m-2 h-screen p-2 text-PurpleNavy rounded-lg">
      <div class="flex bg-white rounded-lg mx-4 md:mx-auto max-w-md md:max-w-2xl border-2 border-yellow-400">
        <div class="flex items-start px-4 py-6">
          <img
            class="w-12 h-12 rounded-full object-cover mr-4 shadow"
            src="https://assets.untappd.com/photos/2022_07_14/5615fb8a58ce2b5bc705ab1092d73e53_raw.jpg"
            alt="avatar"
          />
          <div class="">
            <div class="flex items-center justify-between">
              <h2 class="text-lg font-semibold text-gray-900 -mt-1">
                Bar BeerCordova{" "}
              </h2>
            </div>
            <p class="text-gray-700">12 SEP 2012</p>
            <p class="mt-3 text-gray-700 text-sm">
              Lorem ipsum dolor sit amet consectetur adipisicing elit. Deserunt
              reprehenderit magnam, provident sint dolores aperiam optio
              suscipit incidunt in, asperiores recusandae! Qui maiores sunt
              quos, animi cum officiis atque magni!
            </p>
            <div class="mt-4 flex items-center">
              <div class="flex mr-2 text-gray-700 text-sm ">
                <svg
                  fill="none"
                  viewBox="0 0 24 24"
                  class="w-6 h-6 mr-1 hover:text-red-500 cursor-pointer"
                  stroke="currentColor"
                >
                  <path
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    stroke-width="2"
                    d="M4.318 6.318a4.5 4.5 0 000 6.364L12 20.364l7.682-7.682a4.5 4.5 0 00-6.364-6.364L12 7.636l-1.318-1.318a4.5 4.5 0 00-6.364 0z"
                  />
                </svg>
                <span>12</span>
              </div>
              <div class="flex mr-2 text-gray-700 text-sm ">
                <svg
                  fill="none"
                  viewBox="0 0 24 24"
                  class="w-6 h-6 mr-1 hover:text-blue-500 cursor-pointer"
                  stroke="currentColor"
                >
                  <path
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    stroke-width="2"
                    d="M17 8h2a2 2 0 012 2v6a2 2 0 01-2 2h-2v4l-4-4H9a1.994 1.994 0 01-1.414-.586m0 0L11 14h4a2 2 0 002-2V6a2 2 0 00-2-2H5a2 2 0 00-2 2v6a2 2 0 002 2h2v4l.586-.586z"
                  />
                </svg>
                <span>8</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Post;
