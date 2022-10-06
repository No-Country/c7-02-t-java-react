import React from "react";

function SearchBusiness() {
  return (
    <>
      <div className="m-2">
        <form class="flex items-center">

          <div class="flex w-full">
            <input
              type="text"
              id="search"
              class="bg-gray-50 border border-gray-300 text-gray-900 text-sm  block w-full pl-4 p-2.5 rounded-lg outline-none hover:outline hover:outline-yellow-400"
              placeholder="Busca por ciudad"
              required=""
            />
           
          </div>
          <button
            type="submit"
            class="inline-flex items-center py-2.5 px-3 ml-2 text-sm font-bold hover:text-PurpleNavy hover:bg-yellow-400 rounded-lg border-2 border-white bg-PurpleNavy text-white "
          >
            <svg
              aria-hidden="true"
              class=" w-5 h-5"
              fill="none"
              stroke="currentColor"
              viewBox="0 0 24 24"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                stroke-linecap="round"
                stroke-linejoin="round"
                stroke-width="2"
                d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"
              ></path>
            </svg>
          </button>
        </form>
      </div>
    </>
  );
}

export default SearchBusiness;
