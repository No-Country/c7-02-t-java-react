import { IoHome, IoMdBeer } from "react-icons/io5";
import { MdOutlineComment } from "react-icons/md";
import { VscSignOut } from "react-icons/vsc";



// const style = {
//   icon: "m-2 text-PurpleNavy w-6 h-6 ",
//   link: "text-xl font-thin my-4 ml-2 text-PurpleNavy hover:font-semibold ",
// };

const linksNav = [
  {
    title: "Inicio",
    icon: <IoHome />,
    link: "/dashboard/main",
  },
  {
    title: "Mis Comentarios",
    icon: <MdOutlineComment />,
    link: "/dashboard/posts",
  },
  {
    title: "donde ir?",
    icon: <IoMdBeer />,
    link: "/dashboard/business",
  },
  {
    title: "Inicio",
    icon: <VscSignOut />,
    link: "/",
  },
];

export default linksNav;
