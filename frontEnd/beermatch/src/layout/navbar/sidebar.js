import Link from "next/link";

const style = {
  icon: "m-2 text-PurpleNavy w-6 h-6 ",
  link:"text-xl font-thin my-4 ml-2 text-PurpleNavy hover:font-semibold "
};

export default function Sidebar() {

  return (
    <aside className="lg:visible invisible bg-violet-100 h-full absolute w-60 z-40">
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
          <li className={style.link}>
            <Link href="/dashboard/main" >Inicio</Link>
          </li>
          <li className={style.link}>
            <Link href="/dashboard/posts" className={style.link}>Mis comentarios</Link>
          </li>
          <li className={style.link}>
            <Link href="/dashboard/business" className={style.link}>donde ir?</Link>
          </li>
          <li className={style.link}>
            <Link href="/" className={style.link}>Cerrar sesion</Link>
          </li>
        </ul>
      </div>
    </aside>
  );
}
