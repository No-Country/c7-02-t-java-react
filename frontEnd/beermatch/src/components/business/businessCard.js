import React from "react";

function BusinessCard() {
  return (
    <>
      <div class="flex items-center justify-center ">
        {" "}
        <div class="rounded-xl border-2 border-yellow-400 p-5 shadow-md w-9/12 bg-white">
          <div class="flex w-full items-center justify-between border-b pb-3">
            <div class="flex items-center space-x-3">
            <img
            class="w-12 h-12 rounded-full object-cover mr-4 shadow"
            src="https://assets.untappd.com/photos/2022_07_14/5615fb8a58ce2b5bc705ab1092d73e53_raw.jpg"
            alt="avatar"
          />              <div class="text-lg font-bold text-slate-700">Bar BeerCordova</div>
            </div>
            <div class="flex items-center space-x-8">
              <button class="rounded-2xl border bg-neutral-100 px-3 py-1 text-xs font-semibold">
                Tipo cerveza
              </button>
            </div>
          </div>

          <div class="mt-4 mb-6">
            <div class="mb-3 text-xl font-bold">
              Nulla sed leo tempus, feugiat velit vel, rhoncus neque?
            </div>
            <div class="text-sm text-neutral-600">
              Aliquam a tristique sapien, nec bibendum urna. Maecenas convallis
              dignissim turpis, non suscipit mauris interdum at. Morbi sed
              gravida nisl, a pharetra nulla. Etiam tincidunt turpis leo, ut
              mollis ipsum consectetur quis. Etiam faucibus est risus, ac
              condimentum mauris consequat nec. Curabitur eget feugiat massa
            </div>
          </div>

          <div>
            <div class="flex items-center justify-between text-slate-500">
              <div class="flex space-x-4 md:space-x-8">
                <div class="flex cursor-pointer items-center transition hover:text-slate-600">
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    class="mr-1.5 h-5 w-5"
                    fill="none"
                    viewBox="0 0 24 24"
                    stroke="currentColor"
                    stroke-width="2"
                  >
                    <path
                      stroke-linecap="round"
                      stroke-linejoin="round"
                      d="M7 8h10M7 12h4m1 8l-4-4H5a2 2 0 01-2-2V6a2 2 0 012-2h14a2 2 0 012 2v8a2 2 0 01-2 2h-3l-4 4z"
                    />
                  </svg>
                  <span>13</span>
                </div>
                <div class="flex cursor-pointer items-center transition hover:text-slate-600">
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
                  <span>4</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default BusinessCard;
