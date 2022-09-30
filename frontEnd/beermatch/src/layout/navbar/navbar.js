import { useState } from "react";
import Link from "next/link";
import { FaUserCircle } from "react-icons/fa";
import { IoNotificationsOutline } from "react-icons/io5";
import Sidebar from "./sideBar";

const style = {
  icon: "m-2 text-yellow-300 w-6 h-6 ",
  link:"text-xl font-thin my-4 text-yellow-300 "
};

function MobileNav({ open, setOpen }) {
  return (
    <div
      className={`absolute top-0 left-0 h-screen w-screen lg:hidden bg-PurpleNavy transform ${
        open ? "-translate-x-0" : "-translate-x-full"
      } transition-transform duration-300 ease-in-out filter drop-shadow-md `}
    >
      <div className="flex items-center justify-center filter drop-shadow-md bg-PurpleNavy h-20">
        {" "}
        {/*logo container*/}
        <a className="text-xl font-semibold" href="/">
          <img
            className="m-auto justify-center h-auto flex"
            src="/logo.png"
            alt=""
            width={100}
          />
        </a>
      </div>
      <div className="flex flex-col ml-4 ">
        <a
          className={style.link}
          href="/dashboard/main"
          onClick={() =>
            setTimeout(() => {
              setOpen(!open);
            }, 100)
          }
        >
          Inicio
        </a>
        <a
          className={style.link}
          href="/dashboard/posts"
          onClick={() =>
            setTimeout(() => {
              setOpen(!open);
            }, 100)
          }
        >
          Mis comentarios
        </a>
        <a
          className={style.link}
          href="/"
          onClick={() =>
            setTimeout(() => {
              setOpen(!open);
            }, 100)
          }
        >
          Cerrar sesion
        </a>
      </div>
    </div>
  );
}

export default function Navbar() {
  const [open, setOpen] = useState(false);
  return (
    <div>
      {/* <Sidebar /> */}
      <div className="sticky top-0">
        <nav className="flex filter drop-shadow-md bg-PurpleNavy px-4 py-4 h-20 items-center">
          <MobileNav open={open} setOpen={setOpen} />
          <div className="w-3/12 flex items-center">
            <a className="text-2xl font-semibold" href="/">
              <img
                className="m-auto justify-center h-auto flex md:hidden"
                src="/logo.png"
                alt=""
                width={100}
              />
            </a>
          </div>
          
          <div className="w-9/12 flex justify-end items-center">
          <div className="flex mx-4">
              <Link href="/contact">
                <IoNotificationsOutline className={style.icon} />
              </Link>
              <Link href="/contact">
                <FaUserCircle className={style.icon} />
              </Link>
            </div>

            <div
              className="z-50 flex relative w-8 h-8 flex-col justify-between items-center md:hidden"
              onClick={() => {
                setOpen(!open);
              }}
            >
              {/* hamburger button */}
              <span
                className={`h-1 w-full bg-yellow-300 rounded-lg transform transition duration-300 ease-in-out ${
                  open ? "rotate-45 translate-y-3.5" : ""
                }`}
              />
              <span
                className={`h-1 w-full bg-yellow-300  rounded-lg transition-all duration-300 ease-in-out ${
                  open ? "w-0" : "w-full"
                }`}
              />
              <span
                className={`h-1 w-full bg-yellow-300  rounded-lg transform transition duration-300 ease-in-out ${
                  open ? "-rotate-45 -translate-y-3.5" : ""
                }`}
              />
            </div>
          </div>
        </nav>
      </div>
    </div>
  );
}
