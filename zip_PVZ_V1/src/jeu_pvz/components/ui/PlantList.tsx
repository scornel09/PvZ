import React from "react";
import { Plante } from "../../../domains/plants/plants.types";
import PlantItem from "./PlantItem";
import { usePlantListLogic } from "../logic/usePlantListLogic";

interface PlantListProps {
  plants: Plante[];
  onPlantClick: (plante: Plante) => void;
  selectedPlant: Plante | null;
  availableHeight: number;
  money: number; 
}

const PlantList: React.FC<PlantListProps> = ({ plants, onPlantClick, selectedPlant, availableHeight, money }) => {
  const plantSize = usePlantListLogic(availableHeight);

  return (
    <div
      className="Plante-list"
      style={{
        display: "flex",
        flexWrap: "wrap",
        gap: "10px",
        justifyContent: "center",
        alignItems: "center",
        height: availableHeight,
        overflowY: "auto",
      }}
    >
      <h2>Or disponible : {money}</h2>

      {plants.map((plante) => (
        <PlantItem
          key={plante.id_plante}
          Plante={plante}
          onClick={onPlantClick}
          isSelected={selectedPlant !== null && selectedPlant.id_plante === plante.id_plante}
          isDisabled={money < plante.cout}
          plantSize={plantSize} 
        />
      ))}
    </div>
  );
};

export default PlantList;