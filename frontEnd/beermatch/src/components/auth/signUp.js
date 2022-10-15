import React from "react";
import { IoBeerOutline, IoPersonOutline } from "react-icons/io5";
import { BsPersonLinesFill } from "react-icons/bs";
import axios from "axios";
import { GoAlert } from "react-icons/go";
import Link from "next/link";
import { toast, ToastContainer } from 'react-toastify';
import "react-toastify/dist/ReactToastify.css";
import { useRouter } from "next/router";


function SignUpUser() {
  const [firstName, setFirstName] = React.useState("");
  const [lastName, setLastName] = React.useState("");
  const [email, setEmail] = React.useState("");
  const [password, setPassword] = React.useState("");
  const [photo, setPhoto] = React.useState("image");
  const [confirmPassword, setConfirmPassword] = React.useState("");
  const [error, setError] = React.useState("");
  const router = useRouter();

  console.log(firstName);
  console.log(lastName);
  console.log(email);
  console.log(password);
  console.log(photo);
  console.log(confirmPassword);
  console.log(error);

  React.useEffect(() => {
    if (
      password.length >= confirmPassword.length ||
      password.length < confirmPassword.length
    ) {
      if (password != confirmPassword) {
        setError("contraseñas no coinciden");
      } else {
        setError();
      }
    }
  }, [confirmPassword]);

  const baseURL = "http://localhost:8080/auth/register";


  const handleRegisterUser = async (e) => {
    e.preventDefault();
    if (password != confirmPassword) {
      setError("contraseñas no coinciden");
      return;
    }
    await axios
      .post(
        baseURL,
        {
          firstName: firstName,
          lastName: lastName,
          email: email,
          password: password,
          photo: photo,
          confirmPassword: password,
        }
        // { headers }
      )
      .then((response) => {
        console.log(response.data);
        toast.success("Usuario creado !", {
          position: toast.POSITION.TOP_CENTER
        });
        router.push("/landing");
      })
      .catch(error => {
        toast.error("Error de registro, pruebe nuevamente", {
          position: toast.POSITION.TOP_CENTER
        });
        console.log(error.message)
      })
  };

  return (
    <>
      <div className="lg:grid lg:grid-cols-2">
        <div className="lg:bg-PurpleNavy lg:h-screen lg:w-full lg:flex lg:visible">
          <img
            className="lg:m-auto lg:justify-center lg:flex hidden"
            src="logo.png"
            alt=""
          />
        </div>
        <div className="flex">
        <ToastContainer autoClose={2000} />

          <div className="flex w-full justify-center items-center bg-white space-y-8">
            <div className="w-full px-8 md:px-32 lg:px-24">
              <form className="bg-white rounded-md shadow-2xl p-5">
                <h1 className="text-gray-800 font-light text-2xl mb-6 flex">
                  Bienvenido al Mejor lugar para los Cerveceros!
                  <IoBeerOutline className="text-yellow-500 mt-1 ml-2" />
                </h1>
                <div className="flex items-center border-2 border-gray-50 mb-6 py-2 px-3 rounded-2xl hover:outline-violet-500 hover:outline hover:outline-1">
                  <IoPersonOutline className="h-5 w-5 text-gray-400 font-light" />
                  <input
                    id="name"
                    className=" pl-2 w-full font-light outline-none border-none"
                    type="text"
                    name="name"
                    placeholder="Nombre"
                    onChange={(e) => setFirstName(e.target.value)}
                  />
                </div>
                <div className="flex items-center border-2 border-gray-50 mb-2 py-2 px-3 rounded-2xl hover:outline-violet-500 hover:outline hover:outline-1">
                  <IoPersonOutline className="h-5 w-5 text-gray-400 font-light" />
                  <input
                    id="lastName"
                    className=" pl-2 w-full font-light outline-none border-none"
                    type="text"
                    name="lastName"
                    placeholder="Apellido"
                    onChange={(e) => setLastName(e.target.value)}
                  />
                </div>
                <div className="justify-end flex m-2">
                  <label className="inline-flex items-center ">
                    <span className="mx-2 text-sm text-gray-400 font-light">
                      Usuario tipo empresa
                    </span>
                    <input
                      type="checkbox"
                      className="form-checkbox h-5 w-5 text-gray-600 rounded-2xl appearance-none border border-gray-200 bg-gray-50 checked:bg-violet-600"
                    />
                  </label>
                </div>
                <div className="flex items-center border-2 border-gray-50 mb-6 py-2 hover:outline-violet-500 hover:outline hover:outline-1 px-3 rounded-2xl">
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
                    placeholder="Correo electrónico"
                    onChange={(e) => setEmail(e.target.value)}
                  />
                </div>
                <div className="flex items-center border-2 border-gray-50 mb-6 py-2 px-3 rounded-2xl hover:outline-violet-500 hover:outline hover:outline-1 ">
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    className="h-5 w-5 text-gray-400 font-light"
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
                    className="pl-2 w-full font-light outline-none border-none"
                    type="password"
                    name="password"
                    id="password"
                    placeholder="Contraseña"
                    onChange={(e) => setPassword(e.target.value)}
                  />
                </div>
                <div className="flex items-center border-2 border-gray-50 py-2 px-3 rounded-2xl hover:outline-violet-500 hover:outline hover:outline-1 ">
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    className="h-5 w-5 text-gray-400 font-light"
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
                    className="pl-2 w-full font-light outline-none border-none"
                    type="password"
                    name="confirmPassword"
                    id="confirmPassword"
                    placeholder="Confirma contraseña"
                    onChange={(e) => setConfirmPassword(e.target.value)}
                  />
                </div>
                {error && (
                  <p className=" flex justify-center bg-red-100 rounded-lg p-0.5 text-red-600 text-xs">
                    <GoAlert className="mt-1" />
                    {error}
                  </p>
                )}


                <button
                  type="submit"
                  className="block w-full bg-violet-600 py-2 mt-10 rounded-2xl hover:bg-white hover:text-violet-600 outline transition-all duration-100 text-white font-semibold"
                  onClick={handleRegisterUser}
                >
                  Registrarse
                </button>
                <div className="flex justify-between mt-4">
                  <span className="text-sm ml-2 font-light hover:text-violet-500 cursor-pointer duration-100 transition-all">
                    Términos y Condiciones
                  </span>
                  <Link href="/landing">
                    <span className="text-sm ml-2 font-light hover:text-violet-500 cursor-pointer duration-100 transition-all">
                      Ya tengo cuenta
                    </span>
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

export default SignUpUser;
