import Link from "next/link";
import SideItems from "./items";
import linksNav from "./links";

const style = {
  icon: "m-2 text-PurpleNavy w-6 h-6 ",
  link: "text-xl flex font-thin my-4 ml-2 text-PurpleNavy hover:font-semibold ",
};

export default function Sidebar() {
  return (
    <aside className="lg:visible invisible bg-white  h-full absolute w-60 z-40">
      <div className="mt-5">
        <img
          className="m-auto justify-center h-auto flex"
          src="/logo.png"
          alt=""
          width={100}
        />
      </div>
      <div className="mt-10">
        <ul className="m-2">
          <SideItems />
        </ul>
      </div>
    </aside>
  );
}
