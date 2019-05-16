package com.comodide.rendering.sdont.viz;

import java.util.Map;
import java.util.Set;

import com.comodide.rendering.editor.SchemaDiagram;
import com.comodide.rendering.sdont.model.SDEdge;
import com.comodide.rendering.sdont.model.SDGraph;
import com.comodide.rendering.sdont.model.SDNode;
import com.mxgraph.layout.mxFastOrganicLayout;
import com.mxgraph.model.mxGraphModel;

public class SDMaker
{
    private SDGraph sdGraph;
    private SchemaDiagram schemaDiagram;
    private NodeMaker<Object> vertexMaker;
    private EdgeMaker<Object> edgeMaker;

    private Map<String, Object> vertices;

    public SDMaker(SDGraph sdGraph)
    {
        this.sdGraph = sdGraph;
        // Create an mxgraph
        this.schemaDiagram = new SchemaDiagram();
        // Create the makers for the cells
        this.vertexMaker = new mxVertexMaker(schemaDiagram);
        this.edgeMaker = new mxEdgeMaker(schemaDiagram);
    }

    public SchemaDiagram visualize()
    {
        // Make the graph
        makeGraph();
        // set the layout for the graph
        executeLayout();
        
        // Return the created Schema Diagram
        return this.schemaDiagram;
        
        // Create and display the visualization
//        mxGraphComponent graphComponent = new mxGraphComponent(this.schemaDiagram);
//
//        return graphComponent;
    }

    private void makeGraph()
    {
        // Get default parent
        Object parent = this.schemaDiagram.getDefaultParent();
        // Make all vertices
        makeVertices(parent);
        // Make all edges
        makeEdges(parent);
    }

    private void executeLayout()
    {
        Object parent = schemaDiagram.getDefaultParent();
        mxFastOrganicLayout fol = new mxFastOrganicLayout(this.schemaDiagram);
        fol.execute(parent);
    }

    private void makeVertices(Object parent)
    {
        // Get all nodes from sdgraph
        Set<SDNode> nodeset = this.sdGraph.getNodeSet();
        // Create the visualizations of the nodes
        this.vertices = this.vertexMaker.makeNodes(nodeset);
        // Add them all to the graph
        this.schemaDiagram.getModel().beginUpdate();
        try
        {
            for (Object vertex : this.vertices.values())
            {
                this.schemaDiagram.addCell(vertex);
            }
        } finally
        {
            this.schemaDiagram.getModel().endUpdate();
        }
    }

    /**
     * Unfortunately, due to limitations of the implementations of creating and
     * adding an edge, the source and target of an edge are only set when they are
     * added to the model. This breaks our implementation of making the edge and
     * adding the edge to the model, separately. Thus, in the makeEdges call above,
     * we use insertEdge instead of createEdge, thus removing the need for an
     * analogous (to makeNodes) update try/catch block here.
     * 
     * @param parent
     */
    private void makeEdges(Object parent)
    {
        // Get all edges from the sdgraph
        Set<SDEdge> edgeset = this.sdGraph.getEdgeSet();
        // Create the visualizations of the edges
        this.edgeMaker.makeEdges(edgeset, this.vertices);
    }
}
