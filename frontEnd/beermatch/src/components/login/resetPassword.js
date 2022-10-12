import Link from "next/link";
import React from "react";
import { IoBeerOutline } from "react-icons/io5";

function ResetPassword() {
  return (
    <>
      <div className="grid grid-cols-2">
        <div className="bg-MiddleYellow h-screen w-full flex">
          <img className="m-auto justify-center flex" src="logo.png" alt="" />
        </div>
        <div className="flex">
          <div className="flex w-full justify-center items-center bg-white space-y-8">
            <div className="w-full px-8 md:px-32 lg:px-24">
              <form className="bg-white rounded-md shadow-2xl p-5">
                <h1 className="text-gray-800 font-light text-2xl mb-6 flex">
                  Hola Cervecero!
                  <IoBeerOutline className="text-yellow-500 mt-1 ml-2" />
                </h1>
                <div className="font-thin text-sm text-gray-800 mb-4">
                  <p>
                    Ingresa el correo electronico con el cual te registraste y
                    te enviamos un correo para restablecer tu contrasena
                  </p>
                </div>
                <div className="flex items-center border-2 border-gray-50 mb-6 py-2 px-3 rounded-2xl hover:outline-violet-500 hover:outline hover:outline-1 ">
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    className="h-5 w-5 text-gray-400 font-light"
                    fill="none"
                    viewBox="0 0 24 24"
                    stroke="currentColor"
                  >
                    <path
                      strokeLinecap="round"
                      strokeLinejoin="round"
                      strokeWidth="1"
                      d="M16 12a4 4 0 10-8 0 4 4 0 008 0zm0 0v1.5a2.5 2.5 0 005 0V12a9 9 0 10-9 9m4.5-1.206a8.959 8.959 0 01-4.5 1.207"
                    />
                  </svg>
                  <input
                    id="email"
                    className=" pl-2 w-full font-light outline-none border-none"
                    type="email"
                    name="email"
                    placeholder="Correo electronico"
                  />
                </div>
                <button
                  type="submit"
                  className="block w-full bg-violet-600 py-2 rounded-2xl hover:bg-white hover:text-violet-600 outline transition-all duration-100 text-white font-semibold"
                >
                  Enviar
                </button>
                <div className="mt-2">
                  <Link href="/landing">
                    <a className="text-sm ml-2  font-light hover:text-violet-500 cursor-pointer duration-100 transition-all">
                      Volver a login
                    </a>
                  </Link>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default ResetPassword;
