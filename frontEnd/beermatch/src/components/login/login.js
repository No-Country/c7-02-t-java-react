import React from "react";
import { IoBeerOutline } from 'react-icons/io5';


function Login() {
  return (
    <>
      <div class="grid grid-cols-2">
        <div class="bg-MiddleYellow h-screen w-full flex">
          <img class="m-auto justify-center flex" src="logo.png" alt="" />
        </div>
        <div class="flex">
          <div class="flex w-full justify-center items-center bg-white space-y-8">
            <div class="w-full px-8 md:px-32 lg:px-24">
              <form class="bg-white rounded-md shadow-2xl p-5">
                <h1 class="text-gray-800 font-light text-2xl mb-6 flex">
                  Hola Cervecero!
                  <IoBeerOutline className="text-yellow-500 mt-1 ml-2"/>
                </h1>
                <div class="flex items-center border-2 border-gray-50 mb-6 py-2 px-3 rounded-2xl">
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    class="h-5 w-5 text-gray-400 font-light"
                    fill="none"
                    viewBox="0 0 24 24"
                    stroke="currentColor"
                  >
                    <path
                      strokeLinecap="round"
                      strokeLinejoin="round"
                      strokeWidth="2"
                      d="M16 12a4 4 0 10-8 0 4 4 0 008 0zm0 0v1.5a2.5 2.5 0 005 0V12a9 9 0 10-9 9m4.5-1.206a8.959 8.959 0 01-4.5 1.207"
                    />
                  </svg>
                  <input
                    id="email"
                    class=" pl-2 w-full font-light outline-none border-none"
                    type="email"
                    name="email"
                    placeholder="Correo electronico"
                  />
                </div>
                <div class="flex items-center border-2 border-gray-50 mb-6 py-2 px-3 rounded-2xl ">
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    class="h-5 w-5 text-gray-400 font-light"
                    viewBox="0 0 20 20"
                    fill="currentColor"
                  >
                    <path
                      fillRule="evenodd"
                      d="M5 9V7a5 5 0 0110 0v2a2 2 0 012 2v5a2 2 0 01-2 2H5a2 2 0 01-2-2v-5a2 2 0 012-2zm8-2v2H7V7a3 3 0 016 0z"
                      clipRule="evenodd"
                    />
                  </svg>
                  <input
                    class="pl-2 w-full font-light outline-none border-none"
                    type="password"
                    name="password"
                    id="password"
                    placeholder="Contraseña"
                  />
                </div>
                <button
                  type="submit"
                  class="block w-full bg-violet-600 py-2 rounded-2xl hover:bg-violet-700 transition-all duration-100 text-white font-semibold"
                >
                  Ingresar
                </button>
                <div class="flex justify-between mt-4">
                  <span class="text-sm ml-2 hover:text-violet-500 cursor-pointer duration-100 transition-all">
                    ¿Olvidaste tu contraseña?
                  </span>

                  <a
                    href="#"
                    class="text-sm ml-2 hover:text-violet-500 cursor-pointer duration-100 transition-all"
                  >
                    Registrarse
                  </a>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default Login;
