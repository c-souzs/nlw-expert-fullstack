import { Button } from "@/components/ui/button";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { getAllTechs } from "@/service/tech";
import { ChevronRight } from "lucide-react";
import { FormEvent, useEffect, useState } from "react";

export default function Home() {
  const [techs, setTechs] = useState<string[]>([]);
  const [loadingTechs, setLoadingTechs] = useState<boolean>(false);
  const [errorTechs, setErrorTechs] = useState<string>("");

  // FORM
  const [techCertification, setTechCertification] = useState<string>("");

  const onValueChangeTech = (value: string) => {
    setTechCertification(value);
  }

  const onSubmitCertification = (e: FormEvent) => {
    e.preventDefault();

    console.log(techCertification);
  }

  const requestTechs = async () => {
    setLoadingTechs(true);
    setErrorTechs("");

    try {
      const response = await getAllTechs();
      console.log(response);
    } catch (error) {
      setErrorTechs("Erro ao buscar tecnologias");
    } finally {
      setLoadingTechs(false);
    }
  }

  useEffect(() => {
    requestTechs();
  }, []);

  return (
    <main className="bg-black h-screen">
      <div className="text-white h-full flex justify-center items-center">
        <section>
          <h1 className="text-4xl text-purple-500 text-center font-semibold mb-2">Retire suas certificações <br /> Certificações 📑</h1>
          <p className="text-sm">Dê o seu melhor, sua nota pode entrar para nosso ranking.</p>
          <div className="mt-20">
            <h3 className="font-bold text-center mb-2">Selecione sua tecnologia abaixo.</h3>
            <form onSubmit={onSubmitCertification} className="flex gap-4">
              <Select
                defaultValue=""
                onValueChange={onValueChangeTech}
              >
                <SelectTrigger className="">
                  <SelectValue placeholder="Selecione" />
                </SelectTrigger>
                <SelectContent>
                  <SelectItem value="m@example.com">m@example.com</SelectItem>
                  <SelectItem value="m@google.com">m@google.com</SelectItem>
                  <SelectItem value="m@support.com">m@support.com</SelectItem>
                </SelectContent>
              </Select>
              {
                techCertification && (
                  <Button variant="outline" size="icon">
                    <ChevronRight className="h-4 w-4" />
                  </Button>
                )
              }
            </form>
          </div>
        </section>
      </div>
    </main>
  )
}